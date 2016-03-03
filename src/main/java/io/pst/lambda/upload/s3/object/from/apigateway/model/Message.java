/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.model;

/**
 * @author Borja Lopez Altarriba
 *
 */
public class Message {
    
    private String id;
    private String text;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Message other = (Message) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else
            if (!id.equals(other.id)) {
                return false;
            }
        if (text == null) {
            if (other.text != null) {
                return false;
            }
        } else
            if (!text.equals(other.text)) {
                return false;
            }
        return true;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return text;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.text = name;
    }
    
    @Override
    public String toString() {
        return "Message [id=" + id + ", name=" + text + "]";
    }
}
