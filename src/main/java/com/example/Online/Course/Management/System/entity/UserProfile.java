package com.example.Online.Course.Management.System.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private long phoneNumber;
    private String address;
    @Lob                                             // @Lob stands for Large Object.
    @Column(columnDefinition = "LongBlob")           // LONGBLOB -> 4GB
    private byte[] profilePicture;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

    @OneToOne(mappedBy = "userProfile")
    private User user;

}
