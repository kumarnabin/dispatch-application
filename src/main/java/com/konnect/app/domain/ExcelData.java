package com.konnect.app.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A ExcelData.
 */
@Entity
@Table(name = "excel_data")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "column_1")
    private String column1;

    @Column(name = "column_2")
    private String column2;

    @Column(name = "column_3")
    private String column3;

    @Column(name = "column_4")
    private String column4;

    @Column(name = "column_5")
    private String column5;

    @Column(name = "column_6")
    private String column6;

    @Column(name = "column_7")
    private String column7;

    @Column(name = "column_8")
    private String column8;

    @Column(name = "column_9")
    private String column9;

    @Column(name = "column_10")
    private String column10;

    @Column(name = "column_11")
    private String column11;

    @Column(name = "column_12")
    private String column12;

    @Column(name = "column_13")
    private String column13;

    @Column(name = "column_14")
    private String column14;

    @Column(name = "column_15")
    private String column15;

    @Column(name = "column_16")
    private String column16;

    @Column(name = "column_17")
    private String column17;

    @Column(name = "column_18")
    private String column18;

    @Column(name = "column_19")
    private String column19;

    @Column(name = "column_20")
    private String column20;

    @Column(name = "column_21")
    private String column21;

    @Column(name = "column_22")
    private String column22;

    @Column(name = "column_23")
    private String column23;

    @Column(name = "column_24")
    private String column24;

    @Column(name = "column_25")
    private String column25;

    @Column(name = "column_26")
    private String column26;

    @Column(name = "column_27")
    private String column27;

    @Column(name = "publication_date")
    private Instant publicationDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ExcelData id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn1() {
        return this.column1;
    }

    public ExcelData column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return this.column2;
    }

    public ExcelData column2(String column2) {
        this.setColumn2(column2);
        return this;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return this.column3;
    }

    public ExcelData column3(String column3) {
        this.setColumn3(column3);
        return this;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return this.column4;
    }

    public ExcelData column4(String column4) {
        this.setColumn4(column4);
        return this;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return this.column5;
    }

    public ExcelData column5(String column5) {
        this.setColumn5(column5);
        return this;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return this.column6;
    }

    public ExcelData column6(String column6) {
        this.setColumn6(column6);
        return this;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return this.column7;
    }

    public ExcelData column7(String column7) {
        this.setColumn7(column7);
        return this;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    public String getColumn8() {
        return this.column8;
    }

    public ExcelData column8(String column8) {
        this.setColumn8(column8);
        return this;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    public String getColumn9() {
        return this.column9;
    }

    public ExcelData column9(String column9) {
        this.setColumn9(column9);
        return this;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    public String getColumn10() {
        return this.column10;
    }

    public ExcelData column10(String column10) {
        this.setColumn10(column10);
        return this;
    }

    public void setColumn10(String column10) {
        this.column10 = column10;
    }

    public String getColumn11() {
        return this.column11;
    }

    public ExcelData column11(String column11) {
        this.setColumn11(column11);
        return this;
    }

    public void setColumn11(String column11) {
        this.column11 = column11;
    }

    public String getColumn12() {
        return this.column12;
    }

    public ExcelData column12(String column12) {
        this.setColumn12(column12);
        return this;
    }

    public void setColumn12(String column12) {
        this.column12 = column12;
    }

    public String getColumn13() {
        return this.column13;
    }

    public ExcelData column13(String column13) {
        this.setColumn13(column13);
        return this;
    }

    public void setColumn13(String column13) {
        this.column13 = column13;
    }

    public String getColumn14() {
        return this.column14;
    }

    public ExcelData column14(String column14) {
        this.setColumn14(column14);
        return this;
    }

    public void setColumn14(String column14) {
        this.column14 = column14;
    }

    public String getColumn15() {
        return this.column15;
    }

    public ExcelData column15(String column15) {
        this.setColumn15(column15);
        return this;
    }

    public void setColumn15(String column15) {
        this.column15 = column15;
    }

    public String getColumn16() {
        return this.column16;
    }

    public ExcelData column16(String column16) {
        this.setColumn16(column16);
        return this;
    }

    public void setColumn16(String column16) {
        this.column16 = column16;
    }

    public String getColumn17() {
        return this.column17;
    }

    public ExcelData column17(String column17) {
        this.setColumn17(column17);
        return this;
    }

    public void setColumn17(String column17) {
        this.column17 = column17;
    }

    public String getColumn18() {
        return this.column18;
    }

    public ExcelData column18(String column18) {
        this.setColumn18(column18);
        return this;
    }

    public void setColumn18(String column18) {
        this.column18 = column18;
    }

    public String getColumn19() {
        return this.column19;
    }

    public ExcelData column19(String column19) {
        this.setColumn19(column19);
        return this;
    }

    public void setColumn19(String column19) {
        this.column19 = column19;
    }

    public String getColumn20() {
        return this.column20;
    }

    public ExcelData column20(String column20) {
        this.setColumn20(column20);
        return this;
    }

    public void setColumn20(String column20) {
        this.column20 = column20;
    }

    public String getColumn21() {
        return this.column21;
    }

    public ExcelData column21(String column21) {
        this.setColumn21(column21);
        return this;
    }

    public void setColumn21(String column21) {
        this.column21 = column21;
    }

    public String getColumn22() {
        return this.column22;
    }

    public ExcelData column22(String column22) {
        this.setColumn22(column22);
        return this;
    }

    public void setColumn22(String column22) {
        this.column22 = column22;
    }

    public String getColumn23() {
        return this.column23;
    }

    public ExcelData column23(String column23) {
        this.setColumn23(column23);
        return this;
    }

    public void setColumn23(String column23) {
        this.column23 = column23;
    }

    public String getColumn24() {
        return this.column24;
    }

    public ExcelData column24(String column24) {
        this.setColumn24(column24);
        return this;
    }

    public void setColumn24(String column24) {
        this.column24 = column24;
    }

    public String getColumn25() {
        return this.column25;
    }

    public ExcelData column25(String column25) {
        this.setColumn25(column25);
        return this;
    }

    public void setColumn25(String column25) {
        this.column25 = column25;
    }

    public String getColumn26() {
        return this.column26;
    }

    public ExcelData column26(String column26) {
        this.setColumn26(column26);
        return this;
    }

    public void setColumn26(String column26) {
        this.column26 = column26;
    }

    public String getColumn27() {
        return this.column27;
    }

    public ExcelData column27(String column27) {
        this.setColumn27(column27);
        return this;
    }

    public void setColumn27(String column27) {
        this.column27 = column27;
    }

    public Instant getPublicationDate() {
        return this.publicationDate;
    }

    public ExcelData publicationDate(Instant publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExcelData)) {
            return false;
        }
        return getId() != null && getId().equals(((ExcelData) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExcelData{" +
            "id=" + getId() +
            ", column1='" + getColumn1() + "'" +
            ", column2='" + getColumn2() + "'" +
            ", column3='" + getColumn3() + "'" +
            ", column4='" + getColumn4() + "'" +
            ", column5='" + getColumn5() + "'" +
            ", column6='" + getColumn6() + "'" +
            ", column7='" + getColumn7() + "'" +
            ", column8='" + getColumn8() + "'" +
            ", column9='" + getColumn9() + "'" +
            ", column10='" + getColumn10() + "'" +
            ", column11='" + getColumn11() + "'" +
            ", column12='" + getColumn12() + "'" +
            ", column13='" + getColumn13() + "'" +
            ", column14='" + getColumn14() + "'" +
            ", column15='" + getColumn15() + "'" +
            ", column16='" + getColumn16() + "'" +
            ", column17='" + getColumn17() + "'" +
            ", column18='" + getColumn18() + "'" +
            ", column19='" + getColumn19() + "'" +
            ", column20='" + getColumn20() + "'" +
            ", column21='" + getColumn21() + "'" +
            ", column22='" + getColumn22() + "'" +
            ", column23='" + getColumn23() + "'" +
            ", column24='" + getColumn24() + "'" +
            ", column25='" + getColumn25() + "'" +
            ", column26='" + getColumn26() + "'" +
            ", column27='" + getColumn27() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
