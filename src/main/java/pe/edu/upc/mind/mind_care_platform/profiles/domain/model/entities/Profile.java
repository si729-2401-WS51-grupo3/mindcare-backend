package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.entities;

import jakarta.persistence.*;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.*;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity.AuditableModel;


public class Profile extends AuditableModel {

    @Embedded
    private PersonName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))})
    EmailAddress email;

    @Embedded
    private PhoneNumber phone;
    @Embedded
    private PhotoUrlImage photoUrl;
    @Embedded
    private OccupationUser occupation;
    @Embedded
    private DescriptionUser description;
    @Embedded
    private GenderUser gender;
    @Embedded
    private BirthDateUser birthDate;
    private Role role;

    public Profile(String firstName, String lastName, String email, String phone, String photoUrl, String occupation, String description, String gender, String birthDate, String role) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.phone = new PhoneNumber(phone);
        this.photoUrl = new PhotoUrlImage(photoUrl);
        this.occupation = new OccupationUser(occupation);
        this.description = new DescriptionUser(description);
        this.gender=new GenderUser(gender);
        this.birthDate=new BirthDateUser(birthDate);
        this.role = Role.valueOf(role);
    }

    public Profile() {
    }
    public void update(String email, String phone) {
        this.email = new EmailAddress(email);
        this.phone = new PhoneNumber(phone);
    }
    public void updateEmail(String email) {

        this.email = new EmailAddress(email);
    }
    public void updatePhone(String phone) {
        this.phone = new PhoneNumber(phone);
    }

    public String getFullName() {
        return name.getFullName();
    }
    public String getEmailAddress() {
        return email.address();
    }
    public String getPhoneNumber() {
        return phone.phoneNumber();
    }
    public String getPhotoUrl() {
        return photoUrl.url();
    }
    public String getOccupation() {
        return occupation.occupation();
    }
    public String getDescription() {
        return description.description();
    }
    public String getGender() {
        return gender.gender();
    }
    public String getBirthDate() {
        return birthDate.birthDate();
    }
    public String getRole() {
        return role.name();
    }
}