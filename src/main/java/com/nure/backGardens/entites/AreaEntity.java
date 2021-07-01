package com.nure.backGardens.entites;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "area_table")
@Data
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;

    @Column
    private long width;
    @Column
    private long height;

    @OneToMany
    @JoinColumn(name = "area_id")
    @JsonIgnore
    private List<IotEntity> iotEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public List<IotEntity> getIotEntities() {
        return iotEntities;
    }

    public void setIotEntities(List<IotEntity> iotEntities) {
        this.iotEntities = iotEntities;
    }
}

