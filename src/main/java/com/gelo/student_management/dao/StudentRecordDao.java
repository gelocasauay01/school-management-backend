package com.gelo.student_management.dao;

import com.gelo.student_management.enums.RecordType;
import com.gelo.student_management.model.StudentRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentRecordDao {
    private Long id;
    private Long studentId;
    private Long subjectClassId;
    private RecordType recordType;
    private Double scorePercentage;

    public static StudentRecordDao fromModel(StudentRecord studentRecord) {
        return new StudentRecordDao(
            studentRecord.getId(),
            studentRecord.getStudent().getId(),
            studentRecord.getSubjectClass().getId(),
            studentRecord.getRecordType(),
            studentRecord.getScorePercentage()
        );
    }
}
