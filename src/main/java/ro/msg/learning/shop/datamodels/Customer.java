package ro.msg.learning.shop.datamodels;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@EdmEntityType
@EdmEntitySet
public class Customer {
    @Id
    @GeneratedValue
    @EdmKey
    @EdmProperty
    private int id;

    @EdmProperty
    private String firstName;

    @EdmProperty
    private String lastName;

    @EdmProperty
    private String username;
}
