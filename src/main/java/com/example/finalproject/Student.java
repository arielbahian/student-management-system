package com.example.finalproject;

import javafx.beans.property.*;

public class Student {

    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty middleName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty sex = new SimpleStringProperty();
    private final StringProperty birthdate = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty studentNumber = new SimpleStringProperty();
    private final StringProperty course = new SimpleStringProperty();
    private final StringProperty yearLevel = new SimpleStringProperty();

    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public Student(String firstName, String middleName, String lastName, String sex, String birthdate, String phone,
                   String address, String email, String studentNumber, String course, String yearLevel) {
        this.firstName.set(firstName);
        this.middleName.set(middleName);
        this.lastName.set(lastName);
        this.sex.set(sex);
        this.birthdate.set(birthdate);
        this.phone.set(phone);
        this.address.set(address);
        this.email.set(email);
        this.studentNumber.set(studentNumber);
        this.course.set(course);
        this.yearLevel.set(yearLevel);
    }

    // Getters
    public String getFirstName() { return firstName.get(); }
    public String getMiddleName() { return middleName.get(); }
    public String getLastName() { return lastName.get(); }
    public String getSex() { return sex.get(); }
    public String getBirthdate() { return birthdate.get(); }
    public String getPhone() { return phone.get(); }
    public String getAddress() { return address.get(); }
    public String getEmail() { return email.get(); }
    public String getStudentNumber() { return studentNumber.get(); }
    public String getCourse() { return course.get(); }
    public String getYearLevel() { return yearLevel.get(); }
    public boolean isSelected() { return selected.get(); }

    // Setters
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public void setMiddleName(String middleName) { this.middleName.set(middleName); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public void setSex(String sex) { this.sex.set(sex); }
    public void setBirthdate(String birthdate) { this.birthdate.set(birthdate); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public void setAddress(String address) { this.address.set(address); }
    public void setEmail(String email) { this.email.set(email); }
    public void setStudentNumber(String studentNumber) { this.studentNumber.set(studentNumber); }
    public void setCourse(String course) { this.course.set(course); }
    public void setYearLevel(String yearLevel) { this.yearLevel.set(yearLevel); }
    public void setSelected(boolean selected) { this.selected.set(selected); }

    // Properties
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty middleNameProperty() { return middleName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty sexProperty() { return sex; }
    public StringProperty birthdateProperty() { return birthdate; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty addressProperty() { return address; }
    public StringProperty emailProperty() { return email; }
    public StringProperty studentNumberProperty() { return studentNumber; }
    public StringProperty courseProperty() { return course; }
    public StringProperty yearLevelProperty() { return yearLevel; }
    public BooleanProperty selectedProperty() { return selected; }
}
