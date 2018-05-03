package co.id.ionsoft.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import co.id.ionsoft.utils.Constant;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

@Entity(tableName = Constant.TABLE_GITHUB_REPOSITORY)
public class GithubEntity {

    @PrimaryKey
    private Long id;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("clone_url")
    private String cloneUrl;

    private String language;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
