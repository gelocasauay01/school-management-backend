package com.gelo.student_management.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gelo.student_management.dao.FinalGrade;
import com.gelo.student_management.dao.StudentRecordDao;
import com.gelo.student_management.dto.StudentRecordDTO;
import com.gelo.student_management.enums.RecordType;
import com.gelo.student_management.exceptions.NoRecordsException;
import com.gelo.student_management.model.SubjectClass;
import com.gelo.student_management.model.Student;
import com.gelo.student_management.model.StudentRecord;
import com.gelo.student_management.repository.StudentRecordRepository;

@Service
public class StudentRecordService {

    @Autowired private StudentRecordRepository studentRecordRepository;
    @Autowired private StudentService studentService;
    @Autowired private SubjectClassService subjectClassService;

    public StudentRecordDao createStudentRecord(StudentRecordDTO newStudentRecordDTO) throws ParseException {
        StudentRecord newStudentRecord = convertDTO(newStudentRecordDTO);
        return StudentRecordDao.fromModel(studentRecordRepository.save(newStudentRecord));
    }

    public List<StudentRecordDao> getAllStudentRecords() {
        ArrayList<StudentRecordDao> studentRecordsDao = new ArrayList<StudentRecordDao>();
        for(StudentRecord studentRecord : studentRecordRepository.findAll()) {
            studentRecordsDao.add(StudentRecordDao.fromModel(studentRecord));
        }
        return studentRecordsDao;
    }

    public List<StudentRecordDao> getStudentRecordsBySubjectClassIdAndStudentId(Long subjectClassId, Long studentId) {
        List<StudentRecord> studentRecords = studentRecordRepository.findAll();
        return filterStudentRecordsBySubjectClassAndStudent(studentRecords, subjectClassId, studentId);
    }

    public FinalGrade getFinalGradeBySubjectClassIdAndStudentId(Long subjectClassId, Long studentId) throws NoRecordsException {
        List<StudentRecordDao> studentRecords = filterStudentRecordsBySubjectClassAndStudent(studentRecordRepository.findAll(), subjectClassId, studentId);

        if(studentRecords.isEmpty()) {
            throw new NoRecordsException("Student: " + studentId + " does not have records of class: " + subjectClassId);
        }

        final Integer MAX_SCORE = 100;
        Double finalGrade = 0.00;
        HashMap<RecordType, Double> gradesPerType = new HashMap<RecordType, Double>();
        HashMap<RecordType, Integer> gradeTypeCount = new HashMap<RecordType, Integer>();

        // Get the total of all score percentage
        // Keep track of how many instance of a record type is added
        for (StudentRecordDao studentRecord : studentRecords) {
            RecordType recordType = studentRecord.getRecordType();
            Double grade = studentRecord.getScorePercentage();
            gradesPerType.put(recordType, gradesPerType.getOrDefault(recordType, 0.00) + grade);
            gradeTypeCount.put(recordType, gradeTypeCount.getOrDefault(recordType, 0) + 1);
        }

        // Get the average for each record type
        // multiply the average by the record type rate
        for(RecordType recordType : RecordType.values()) {
            if(gradeTypeCount.containsKey(recordType)) {
                gradesPerType.put(recordType, gradesPerType.get(recordType) / gradeTypeCount.get(recordType) * recordType.getRate());
            } else {
                gradesPerType.put(recordType, recordType.getRate() * MAX_SCORE);
            }
            finalGrade += gradesPerType.get(recordType);
        }

        return new FinalGrade(studentRecords.get(0).getSubjectClassId(), studentRecords.get(0).getStudentId(), finalGrade);
    }

    public StudentRecordDao getStudentRecord(Long id) {
        return StudentRecordDao.fromModel(studentRecordRepository.findById(id).orElseThrow());
    }

    public StudentRecordDao updateStudentRecord(Long id, StudentRecordDTO updatedStudentRecordDTO) throws ParseException {
        StudentRecord updatedStudentRecord = convertDTO(updatedStudentRecordDTO);
        updatedStudentRecord.setId(id);  
        return StudentRecordDao.fromModel(studentRecordRepository.save(updatedStudentRecord));
    }

    public void deleteAllStudentRecords() {
        studentRecordRepository.deleteAll();
    }

    public void deleteStudentRecord(Long id) {
        studentRecordRepository.deleteById(id);
    } 

    private StudentRecord convertDTO(StudentRecordDTO studentRecordDTO) {
        Student student = studentService.getStudent(studentRecordDTO.getStudentId());
        SubjectClass subjectClass = subjectClassService.getSubjectClass(studentRecordDTO.getSubjectClassId());
        return new StudentRecord(
            student, 
            subjectClass, 
            RecordType.values()[studentRecordDTO.getRecordType()], 
            studentRecordDTO.getScorePercentage()
        );
    }

    private List<StudentRecordDao> filterStudentRecordsBySubjectClassAndStudent(List<StudentRecord> studentRecords, Long subjectClassId, Long studentId) {
        ArrayList<StudentRecordDao> filteredRecords = new ArrayList<StudentRecordDao>();
        for (StudentRecord studentRecord : studentRecords) {
            if(studentRecord.getSubjectClass().getId() == subjectClassId && studentRecord.getStudent().getId() == studentId) {
                filteredRecords.add(StudentRecordDao.fromModel(studentRecord));
            }
        }
        return filteredRecords;
    }
}
