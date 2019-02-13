package ro.msg.learning.shop.datamodels;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@EdmEntityType
@EdmEntitySet
public class Location {
    @Id
    @GeneratedValue
    @EdmKey
    @EdmProperty
    private int id;

    @EdmProperty
    private String name;

    @Embedded
    private Address address;
}
