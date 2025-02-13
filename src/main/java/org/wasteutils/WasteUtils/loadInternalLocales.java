package org.wasteutils.WasteUtils;

import org.wasteutils.WasteUtils.Exceptions.localesFailException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class loadInternalLocales {
    private static final List<String> STRINGS = loadStrings();

    private static List<String> loadStrings() {
        List<String> result = new ArrayList<>();
        try (InputStream is = loadInternalLocales.class.getClassLoader().getResourceAsStream("locales.config")) {
            if (is == null) {
                throw new localesFailException("Locales not found, did you moved/deleted the locale.config?");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    result.add(line.trim());
                }
            }
            return Collections.unmodifiableList(result);
        } catch (IOException e) {
            throw new localesFailException("Locales loading failed. " + e);
        }
    }

    public static List<String> getStrings() {
        return STRINGS;
    }
}