package com.osomapps.pt.tokenemail;

import java.util.HashMap;
import java.util.Map;

final class TemplateEngine {
    private static final String ALL_SYMBOLS = "([\\s\\S]+?)";
    private static final Map<String, String> TEMPLATE_SETTINGS = new HashMap<>();

    static {
        TEMPLATE_SETTINGS.put("evaluate", "\\{\\{" + ALL_SYMBOLS + "\\}\\}");
    }

    interface Template<F> {
        String apply(F arg);
    }

    static final class TemplateImpl<K, V> implements Template<Map<K, V>> {
        private final String template;

        TemplateImpl(String template) {
            this.template = template;
        }

        @Override
        @SuppressWarnings("unchecked")
        public String apply(Map<K, V> value) {
            final String evaluate = TEMPLATE_SETTINGS.get("evaluate");
            String result = template;
            for (final Map.Entry<K, V> element : value.entrySet()) {
                result = java.util.regex.Pattern.compile(evaluate.replace(ALL_SYMBOLS,
                    "\\s*\\Q" + element.getKey()
                    + "\\E\\s*")).matcher(result).replaceAll(String.valueOf(element.getValue()));
            }
            return result;
        }
    }

    static <K, V> TemplateEngine.Template<Map<K, V>> template(final String template) {
        return new TemplateImpl<>(template);
    }

}
