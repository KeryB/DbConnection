package ru.connection.db.utils;


import ru.connection.db.entity.FilterActor;

public class QueryUtils {

    public static String toQuery(Object filter) {
        FilterActor filterActor = (FilterActor) filter;
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (filterActor.getBirthDay() != null) {
            stringBuilder.append(" birthday >= '").append(filterActor.getBirthDay()).append("'");
            count++;
        }
        if (filterActor.getBirthDayTo() != null) {
            if (count > 0) {
                stringBuilder.append(" AND birthday <= '").append(filterActor.getBirthDayTo()).append("'");
            } else {
                stringBuilder.append(" birthday <= '").append(filterActor.getBirthDayTo()).append("'");
            }
            count++;
        }
        if(filterActor.getSalaryActorFrom() != null){
            if (count > 0) {
                stringBuilder.append(" AND salary >= ").append(filterActor.getSalaryActorFrom());
            } else {
                stringBuilder.append(" salary >= ").append(filterActor.getSalaryActorFrom());
            }
            count++;
        }
        if(filterActor.getSalaryActorTo() != null){
            if (count > 0) {
                stringBuilder.append(" AND salary <= ").append(filterActor.getSalaryActorTo());
            } else {
                stringBuilder.append(" salary <= ").append(filterActor.getSalaryActorTo());
            }
            count++;
        }
        if(filterActor.getName() != null){
            if (count > 0) {
                stringBuilder.append(" AND first_name LIKE '%").append(filterActor.getName()).append("%'");
            } else {
                stringBuilder.append(" first_name LIKE '%").append(filterActor.getName()).append("%'");
            }
        }
        return stringBuilder.toString();
    }

}
