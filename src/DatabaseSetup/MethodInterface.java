package DatabaseSetup;

import AllClasses.*;
import java.util.ArrayList;

public interface MethodInterface {

    void addPatient(Patient obj);

    void deletePatient(int id);

    void updatePatient(int id, Patient obj);

    ArrayList<Patient> searchPatient(String name);

    ArrayList<Patient> showPatientData();

    int patientNextId();

    void addDoctor(Doctor obj);

    void deleteDoctor(int id);

    void updateDoctor(int id, Doctor obj);

    ArrayList<Doctor> searchDoctor(String name);

    ArrayList<Doctor> showDoctorData();

    int doctorNextId();

    void addAppointment(Appointment obj);

    void deleteAppointment(int id);

    ArrayList<Appointment> showAppointmentData();

    ArrayList<Appointment> searchAppointment(String patientName);

    int appointmentNextId();

    int billNextId();

    String[] appointmentIds();

    String[] doctorList();

    String[] patientList();

    void addBill(Bill obj);

    ArrayList<Bill> showBillData();

    ArrayList<Bill> billSearch(int appointmentId);

    ArrayList<String[]> patientsByDoctorName(String name);

    String[][] billByDate(String date);

    Object[][] AppointmentDetailBypatientName(String name);

}
