package com.gateway.notification.util;

import java.util.List;

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
}
