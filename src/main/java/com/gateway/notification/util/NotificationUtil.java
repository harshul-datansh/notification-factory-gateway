package com.gateway.notification.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotificationUtil {
    public static String resolveDynamicVarWithValues(List<String> vars, List<String> varValues, String exceptionableName,
                                                String exceptionableValue, String resolved) {
        // Validate  vars
        if (vars == null || varValues == null) {
            throw new IllegalArgumentException(exceptionableName);
        }
        if (vars.size() != varValues.size()) {
            throw new IllegalArgumentException(exceptionableValue);
        }

        // Replace each  placeholder with its corresponding value
        for (int i = 0; i < vars.size(); i++) {
            String placeholder = vars.get(i);
            String value = varValues.get(i);
            if (placeholder != null && value != null) {
                resolved = resolved.replace(placeholder, value);
            }
        }
        return resolved;
    }

    /**
     * Converts a comma-separated string into a list of trimmed, non-empty strings.
     *
     * @param commaSeparated the input string (e.g., "name, age, country")
     * @return a list of individual trimmed values, or an empty list if null/blank
     */
    public static List<String> commaSeperatedToList(String commaSeparated) {
        if (commaSeparated == null || commaSeparated.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Stream.of(commaSeparated.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of strings into a single comma-separated string.
     *
     * @param list the list of strings (e.g., ["name", "age", "country"])
     * @return a comma-separated string (e.g., "name, age, country"), or empty string if list is null/empty
     */
    public static String listToCommaSeparatedString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }

        return list.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(", "));
    }
}
