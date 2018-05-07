package ru.dinis.docs.dto;


import ru.dinis.docs.beans.Task;

/**
 * Create by dinis of 07.05.18.
 */
public class ConverterDto {

    public static Task taskDtoToTask(TaskDto taskDto) {
        Task task = new Task();

        task.setSubject(taskDto.getSubject());
        task.setAuthor(taskDto.getAuthor());
        task.setPerformers(taskDto.getPerformers());
        task.setPeriod(taskDto.getPeriod());
        task.setControl(taskDto.getControl());
        task.setExecution(taskDto.getExecution());
        task.setDescr(taskDto.getDescr());
        return task;
    }

    public static TaskDto taskTotaskDto(Task taskDto) {
        TaskDto task = new TaskDto();
        task.setTaskId(taskDto.getTaskId());
        task.setSubject(taskDto.getSubject());
        task.setAuthor(taskDto.getAuthor());
        task.setPerformers(taskDto.getPerformers());
        task.setPeriod(taskDto.getPeriod());
        task.setControl(taskDto.getControl());
        task.setExecution(taskDto.getExecution());
        task.setDescr(taskDto.getDescr());
        return task;
    }


}
