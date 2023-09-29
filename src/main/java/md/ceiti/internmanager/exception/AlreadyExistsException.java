package md.ceiti.internmanager.exception;

import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.Department;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.entity.Wage;
import md.ceiti.internmanager.entity.WorkRecord;
import md.ceiti.internmanager.util.ExceptionMessage;

public class AlreadyExistsException extends RuntimeException {

    public static final String DEPARTMENT = Department.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String EMPLOYEE = Employee.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String JOB = Job.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String ASSIGNMENT = Assignment.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String PROJECT = Project.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String WAGE = Wage.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;
    public static final String WORK_RECORD = WorkRecord.class.getSimpleName() + ExceptionMessage.ALREADY_EXISTS;

    public AlreadyExistsException(Class<?> clazz) {
        super(clazz.getSimpleName() + ExceptionMessage.ALREADY_EXISTS);
    }
}
