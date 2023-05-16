package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

/** Объект тикета (POJO) */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    // todo: serialized поля, геттеры и сеттеры
    @SerializedName("id")
    private Integer id;

    @JsonIgnore
    public String due_data; // $date необязательное
    @JsonIgnore
    public String assigned_to; // необязательное

    @SerializedName("Title")
    private String title;

    public String created; //$date-time Дата, когда тикет впервые создан
    public String modified; //$date-time  Дата изменения тикета

    @JsonIgnore
    private String submitter_email; //необязательноеThe submitter will receive an email for all public follow-ups left for this task.

    private Integer status;

    private boolean on_hold; // Если билет находится в режиме ожидания, он не будет автоматически продлен.

    @SerializedName("Description")
    private String description; //Содержание запроса клиентов
   // private String resolution; //Разрешение, предоставленное заказчику нашими сотрудниками.

    @SerializedName("Priority")
    private Integer priority;
    //private Date last_escalation;

   // public String secret_key;

    @SerializedName("Queue")
    private Integer queue;

    @JsonIgnore
    public Integer kbitem;
    @JsonIgnore
    public Integer merged_to;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDue_data() {
        return due_data;
    }

    public void setDue_data(String due_data) {
        this.due_data = due_data;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSubmitter_email() {
        return submitter_email;
    }

    public void setSubmitter_email(String submitter_email) {
        this.submitter_email = submitter_email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isOn_hold() {
        return on_hold;
    }

    public void setOn_hold(boolean on_hold) {
        this.on_hold = on_hold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public Integer getKbitem() {
        return kbitem;
    }

    public void setKbitem(Integer kbitem) {
        this.kbitem = kbitem;
    }

    public Integer getMerged_to() {
        return merged_to;
    }

    public void setMerged_to(Integer merged_to) {
        this.merged_to = merged_to;
    }

    @Override
    public boolean equals(Object o) {
        // todo
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                status == ticket.status &&
                on_hold == ticket.on_hold &&
                priority == ticket.priority &&
                queue == ticket.queue &&
                Objects.equals(title, ticket.title) &&
                Objects.equals(submitter_email, ticket.submitter_email) &&
                Objects.equals(description, ticket.description);



    }

    @Override
    public int hashCode() {
        // todo
        return Objects.hash(id, title, submitter_email, status, on_hold, description, priority, queue);
    }
}
