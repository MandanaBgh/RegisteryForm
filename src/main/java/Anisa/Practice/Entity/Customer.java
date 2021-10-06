package Anisa.Practice.Entity;


//import com.github.mfathi91.time.PersianDate;

import com.github.mfathi91.time.PersianDate;
import org.hibernate.hql.spi.id.inline.AbstractInlineIdsBulkIdHandler;

import javax.persistence.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {
    private static PersianDate persianDate;
    private static LocalDate localDate;
    private static int year;
    private static int month;
    private static int day;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @Column(name = "birthday")
    private Date birthday;

    public Customer(String name, String lastname, byte[] image, Date birthday) {
        this.name = name;
        this.lastname = lastname;
        this.image = image;
        this.birthday = birthday;

    }

    public Customer() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getBirthday() {
        if (birthday != null) {
            try {
                ConvertDateTime(birthday);
                localDate = LocalDate.of(this.year, this.month, this.day);
                persianDate = PersianDate.fromGregorian(localDate);
                Date day = null;
                day = new SimpleDateFormat("yyyy-MM-dd").parse(persianDate.toString());
                return day;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public void setBirthday(Date birthday) {
        ConvertDateTime(birthday);
        persianDate = PersianDate.of(this.year, this.month, this.day);
        localDate = persianDate.toGregorian();

        this.birthday = java.sql.Date.valueOf(localDate);

    }

    private void ConvertDateTime(Date birthday) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String Shamsi = dateFormat.format(birthday);


        this.year = Integer.valueOf(Shamsi.substring(0, 4));
        this.month = Integer.valueOf(Shamsi.substring(4, 6));
        this.day = Integer.valueOf(Shamsi.substring(6, 8));

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", image=" + Arrays.toString(image) +
                ", birthday=" + birthday +
                '}';
    }
}