package com.example.bukkinyulszbackend.security.util;


import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtil {

    @SafeVarargs
    public static <T> T isNullOr(T... args) {
        if (args == null)
            return null;
        for (T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }

    public static Throwable getRootCause(Throwable throwable) {
        Throwable rootCause;
        do {
            rootCause = throwable;
            throwable = throwable.getCause();
        } while (throwable != null);
        return rootCause;
    }

    public static List<String> regexMatches(final String regex, final String input) {
        if (input == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return null;
        }
        List<String> matches = new ArrayList<>();
        int groupCount = matcher.groupCount();
        for (int i = 0; i <= groupCount; i++) {
            matches.add(matcher.group(i));
        }
        return matches;
    }

    public static String getMd5(final String string) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        byte[] mdbytes = md.digest(string.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String getMd5( final File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(file);

        byte[] dataBytes = new byte[1024];

        int nread;
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        fis.close();
        byte[] mdbytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String[] toStringArray(final Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        String[] arr = new String[collection.size()];
        int i = 0;
        for (Object elem : collection) {
            arr[i] = (elem == null) ? null : elem.toString();
            i++;
        }
        return arr;
    }

    public static String getAttributesOneValue(final Attributes attributes, final String attrId) {
        if (attributes == null) {
            return null;
        }
        Attribute attribute = attributes.get(attrId);
        if (attribute == null) {
            return null;
        }
        if (attribute.size() == 0) {
            return null;
        }
        Object value;
        try {
            value = attribute.get();
        } catch (NamingException e) {
            return null;
        }
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
