
package com.esprit.models;

/**
 *
 * @author ousse
 */
public class ImageEvent {
    
    private int id;
    private int event_id;
    private String source;
    private int category_id;

    public ImageEvent(int id, int event_id, String source, int category_id) {
        this.id = id;
        this.event_id = event_id;
        this.source = source;
        this.category_id = category_id;
    }

    public ImageEvent(int event_id, String source, int category_id) {
        this.event_id = event_id;
        this.source = source;
        this.category_id = category_id;
    }

    public ImageEvent(int id, int event_id, String source) {
        this.id = id;
        this.event_id = event_id;
        this.source = source;
    }

    public ImageEvent(int event_id, String source) {
        this.event_id = event_id;
        this.source = source;
    }

    public ImageEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "ImageEvent{" + "id=" + id + ", event_id=" + event_id + ", source=" + source + ", category_id=" + category_id + '}';
    }
    
    
    
    
}
