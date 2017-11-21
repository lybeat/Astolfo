package com.arturia.astolfo.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: Arturia
 * Date: 2017/11/21
 */
@Entity
public class SearchHistory {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private Long time;

    @Generated(hash = 1024935339)
    public SearchHistory(Long id, String name, Long time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
