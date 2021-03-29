package com.grlife.keyword_view.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="VIEW")
@Entity
@Setter
@Getter
public class ViewInfo {

    @Id
    @Column(name = "CODE")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String code;

    @Column(name = "I_DATE")
    private String i_date;

    @Builder
    public ViewInfo(String code, String i_date) {
        this.code = code;
        this.i_date = i_date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }
}
