package com.osomapps.pt.tokenemail;

import java.util.HashMap;
import java.util.Map;

class TemplateEngine {
    private static final Map<String, String> TEMPLATE_SETTINGS = new HashMap<String, String>() { {
        put("evaluate", "\\{\\{([\\s\\S]+?)\\}\\}");
    } };
    private static final String ALL_SYMBOLS = "([\\s\\S]+?)";

    private interface Function1<F, T> {
        T apply(F arg);

        @Override
        boolean equals(Object object);
    }

    public interface Template<T> extends Function1<T, String> {
    }

    private static final class TemplateImpl<K, V> implements Template<Map<K, V>> {
        private final String template;

        private TemplateImpl(String template) {
            this.template = template;
        }

        @Override
        @SuppressWarnings("unchecked")
        public String apply(Map<K, V> value) {
            final String evaluate = TEMPLATE_SETTINGS.get("evaluate");
            String result = template;
            for (final Map.Entry<K, V> element : value.entrySet()) {
                result = java.util.regex.Pattern.compile(evaluate.replace(ALL_SYMBOLS,
                    "\\s*\\Q" + ((Map.Entry) element).getKey()
                    + "\\E\\s*")).matcher(result).replaceAll(String.valueOf(((Map.Entry) element).getValue()));
            }
            return result;
        }
    }

    static <K, V> TemplateEngine.Template<Map<K, V>> template(final String template) {
        return new TemplateEngine.TemplateImpl<>(template);
    }

}
