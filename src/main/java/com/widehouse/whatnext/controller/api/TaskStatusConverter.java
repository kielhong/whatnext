package com.widehouse.whatnext.controller.api;

import com.widehouse.whatnext.domain.TaskStatus;

import java.beans.PropertyEditorSupport;

public class TaskStatusConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String string) throws IllegalArgumentException {
        TaskStatus status = TaskStatus.valueOf(string.toUpperCase());
        setValue(status);
    }
}