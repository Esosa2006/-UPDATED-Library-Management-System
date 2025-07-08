package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imageData")
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    @Column(name = "imageName")
    private String imageName;
    @Column(name = "imageType")
    private String imageType;
    @Lob
    @Column(name = "imageData", length = 1000)
    private byte[] imageData;
//    @OneToOne(mappedBy = "passport")
//    private Librarian librarian;

}
