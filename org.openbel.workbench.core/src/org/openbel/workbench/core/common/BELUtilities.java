/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.common;

import static java.io.File.separator;
import static java.lang.Integer.parseInt;
import static java.lang.System.arraycopy;
import static java.lang.Thread.currentThread;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.openbel.workbench.core.common.PathConstants.BEL_SCRIPT_EXTENSION;
import static org.openbel.workbench.core.common.PathConstants.XBEL_EXTENSION;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XXX replace this with a dependency on a BEL framework common?
 */
public class BELUtilities {
    private static int pid = -1;
    private static final long[] byteTable = createLookupTable();
    private static final long HSTART = 0xBB40E64DA205B064L;
    private static final long HMULT = 7664345821815920749L;
    
    /**
     * Returns {@code true} if the collection is non-null and non-empty,
     * {@code false} otherwise.
     * 
     * @param c
     *            Collection, may be null
     * @return boolean
     */
    public static boolean hasItems(final Collection<?> c) {
        return c != null && !c.isEmpty();
    }

    /**
     * Returns {@code true} if the collection is null or empty, {@code false}
     * otherwise.
     * 
     * @param c
     *            Collection, may be null
     * @return boolean
     */
    public static boolean noItems(final Collection<?> c) {
        return !hasItems(c);
    }

    /**
     * Returns {@code true} if the array is non-null and has a length greater
     * than zero, {@code false} otherwise.
     * 
     * @param <T>
     *            Captured array type
     * @param t
     *            Array of type {@code <T>}
     * @return boolean
     */
    public static <T> boolean hasItems(final T[] t) {
        return t != null && t.length > 0;
    }

    /**
     * Returns {@code true} if the array is null or has no elements,
     * {@code false} otherwise.
     * 
     * @param <T>
     *            Captured array type
     * @param t
     *            Array of type {@code <T>}, may be null
     * @return boolean
     */
    public static <T> boolean noItems(final T[] t) {
        return !hasItems(t);
    }

    /**
     * Returns {@code true} if the map is non-null and is non-empty,
     * {@code false} otherwise.
     * 
     * @param <K>
     *            Captured key type
     * @param <V>
     *            Captured value type
     * @param m
     *            Map of type {@code <K, V>}, may be null
     * @return boolean
     */
    public static <K, V> boolean hasItems(final Map<K, V> m) {
        return m != null && !m.isEmpty();
    }

    /**
     * Returns {@code true} if the map is null or empty, {@code false}
     * otherwise.
     * 
     * @param <K>
     *            Captured key type
     * @param <V>
     *            Captured value type
     * @param m
     *            Map of type {@code <K, V>}, may be null
     * @return boolean
     */
    public static <K, V> boolean noItems(final Map<K, V> m) {
        return !hasItems(m);
    }

    /**
     * Returns {@code true} if the string is non-null and non-empty,
     * {@code false} otherwise.
     * 
     * @param s
     *            String, may be null
     * @return boolean
     */
    public static boolean hasLength(final String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * Returns {@code true} if the string is null or empty, {@code false}
     * otherwise.
     * 
     * @param s
     *            String, may be null
     * @return boolean
     */
    public static boolean noLength(final String s) {
        return !hasLength(s);
    }

    /**
     * Returns {@code true} if any {@link String} in <tt>strings</tt> is null or
     * empty, {@code false} otherwise.
     * 
     * @param strings
     *            {@link String[]}, may be null
     * @return boolean
     */
    public static boolean noLength(final String... strings) {
        if (strings == null) {
            return true;
        }

        for (final String string : strings) {
            if (!hasLength(string)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a sized array list of type {@code T}.
     * 
     * @param <T>
     *            Formal type parameter
     * @param size
     *            Array list size
     * @return Array list of type {@code T}
     */
    public static <T> ArrayList<T> sizedArrayList(final int size) {
        return new ArrayList<T>(size);
    }

    /**
     * Returns a hash set of type {@code T} with initial capacity {@code size}.
     * <p>
     * Use sized hash sets when the capacity of a hash set is known to be
     * greater than sixteen (the default initial capacity). The hash set
     * implementation will automatically adjust the size to the next nearest
     * power of two.
     * </p>
     * 
     * @param <T>
     *            Formal type parameter
     * @param size
     *            Hash set initial capacity
     * @return Hash set of type {@code T}
     */
    public static <T> HashSet<T> sizedHashSet(final int size) {
        return new HashSet<T>(size);
    }

    /**
     * Returns a hash set of type {@code T} optimized to the specified capacity
     * and load factor.
     * <p>
     * Use optimized hash sets when the capacity of a hash set is known to be
     * greater than sixteen (the default initial capacity) and a load factor is
     * desired to control resizing behavior. The hash set implementation will
     * automatically adjust the size to the next nearest power of two.
     * </p>
     * 
     * @param <T>
     *            Formal type parameter
     * @param s
     *            Initial hash set capacity
     * @param lf
     *            Hash set load factor
     * @return Hash set of type {@code T}
     */
    public static <T> HashSet<T> optimizedHashSet(final int s, final float lf) {
        return new HashSet<T>(s, lf);
    }

    /**
     * Returns a hash set of type {@code T} optimized to trade memory efficiency
     * for CPU time.
     * <p>
     * Use constrained hash sets when the capacity of a hash set is known to be
     * greater than sixteen (the default initial capacity) and the addition of
     * elements beyond the set's capacity will not occur. The hash set
     * implementation will automatically adjust the size to the next nearest
     * power of two.
     * </p>
     * 
     * @param <T>
     *            Formal type parameter
     * @param s
     *            Initial hash set capacity
     * @return Hash set of type {@code T}
     */
    public static <T> HashSet<T> constrainedHashSet(final int s) {
        return new HashSet<T>(s, 1.0F);
    }

    /**
     * Returns a hash map of type {@code K, V} with initial capacity
     * {@code size}.
     * <p>
     * Use sized hash maps when the capacity of a hash map is known to be
     * greater than sixteen (the default initial capacity). The hash map
     * implementation will automatically adjust the size to the next nearest
     * power of two.
     * </p>
     * 
     * @param <K>
     *            Formal type parameter key
     * @param <V>
     *            Formal type parameter value
     * @param size
     *            Hash map initial capacity
     * @return Hash map of type {@code K, V}
     */
    public static <K, V> HashMap<K, V> sizedHashMap(final int size) {
        return new HashMap<K, V>(size);
    }

    /**
     * Returns a hash map of type {@code K, V} optimized to the specified
     * capacity and load factor.
     * <p>
     * Use optimized hash maps when the capacity of a hash map is known to be
     * greater than sixteen (the default initial capacity) and a load factor is
     * desired to control resizing behavior. The hash map implementation will
     * automatically adjust the size to the next nearest power of two.
     * </p>
     * 
     * @param <K>
     *            Formal type parameter key
     * @param <V>
     *            Formal type parameter value
     * @param s
     *            Initial hash map capacity
     * @param lf
     *            Hash map load factor
     * @return Hash map of type {@code K, V}
     */
    public static <K, V> HashMap<K, V> optimizedHashMap(final int s,
            final float lf) {
        return new HashMap<K, V>(s, lf);
    }

    /**
     * Returns a hash map of type {@code K, V} optimized to trade memory
     * efficiency for CPU time.
     * <p>
     * Use constrained hash maps when the capacity of a hash map is known to be
     * greater than sixteen (the default initial capacity) and the addition of
     * elements beyond the map's capacity will not occur. The hash map
     * implementation will automatically adjust the size to the next nearest
     * power of two.
     * </p>
     * 
     * @param <K>
     *            Formal type parameter key
     * @param <V>
     *            Formal type parameter value
     * @param s
     *            Initial hash map capacity
     * @return Hash map of type {@code K, V}
     */
    public static <K, V> HashMap<K, V> constrainedHashMap(final int s) {
        return new HashMap<K, V>(s, 1.0F);
    }

    /**
     * Returns a hash set of type {@code E} optimized to the
     * {@link Collection#size() size} of the supplied {@link Collection
     * collection}.
     * 
     * @param <E>
     *            Formal type parameter collection element
     * @param c
     *            {@link Collection}; may be null
     * @return Hash set of type {@code E}
     */
    public static <E> HashSet<E> asHashSet(final Collection<E> c) {
        if (noItems(c)) {
            return new HashSet<E>();
        }
        return new HashSet<E>(c);
    }

    /**
     * Returns a {@link Set} view of the entries contained in this map.
     * <p>
     * This method should <strong>always</strong> be preferred instead of
     * iterating a maps keys and invoking {@link Map#get(Object) get()} on each
     * iteration. The following code should never be used:
     * 
     * <pre>
     * <code>
     * Map<String, String> map = [...]
     * for (final String key : map.keySet()) {
     *     String value = map.get(key);
     *     // do something with key/value
     * }
     * </code>
     * </pre>
     * 
     * Instead, write:
     * 
     * <pre>
     * <code>
     * Map<String, String> map = [...]
     * for (final Entry<String, String> e : entries(map)) {
     *     // do something with e.getKey/e.getValue
     * }
     * </code>
     * </pre>
     * 
     * </p>
     * 
     * @param m
     *            {@link Map}; may be null
     * @return {@link Set} of {@link Map.Entry map entries}
     */
    public static <K, V> Set<Map.Entry<K, V>> entries(Map<K, V> m) {
        if (m == null) {
            return emptySet();
        }
        return m.entrySet();
    }

    /**
     * Inserts the platform-specific filesystem path separator between
     * {@code directory} and {@code filename} and returns the resulting string.
     * 
     * @param directory
     *            Non-null string
     * @param filename
     *            Non-null string
     * @return String following the format
     *         {@code directory<path_separator>filename}
     */
    public static String asPath(final String directory, final String filename) {
        return directory.concat(separator).concat(filename);
    }

    /**
     * Inserts the platform-specific filesystem path separator between the
     * provided strings.
     * 
     * @param strings
     * @return String in the following form:
     *         {@code strings[0]<path_separator>strings[1]<path_separator>...<strings[n]>}
     *         , or <b>null</b> if {@code strings} is null
     */
    public static String asPath(final String... strings) {
        if (strings == null)
            return null;
        final StringBuilder bldr = new StringBuilder();
        for (final String string : strings) {
            if (bldr.length() != 0)
                bldr.append(separator);
            bldr.append(string);
        }
        return bldr.toString();
    }

    /**
     * Create the provided directory, if it does not already exist.
     * 
     * @param directory
     *            Path to create
     * @throws RuntimeException
     *             Thrown if directory creation failed
     */
    public static void createDirectory(final String directory) {
        if (directory == null)
            return;
        final File f = new File(directory);
        if (!f.isDirectory()) {
            if (!f.mkdir())
                throw new RuntimeException("couldn't create " + directory);
        }
    }

    /**
     * Create the provided directory, and all necessary subdirectories, if they
     * do not already exist.
     * 
     * @param directory
     *            Path to create
     * @throws RuntimeException
     *             Thrown if directory creation failed
     */
    public static void createDirectories(final String directory) {
        if (directory == null)
            return;
        final File f = new File(directory);
        if (!f.isDirectory()) {
            if (!f.mkdirs())
                throw new RuntimeException("couldn't create " + directory);
        }
    }

    /**
     * Converts into seconds and returns a string in the format
     * {@code <seconds>.<milliseconds>}.
     * 
     * @param milliseconds
     * @return String
     */
    public static String timeFormat(final long milliseconds) {
        double seconds = milliseconds / 1000.0d;
        final NumberFormat fmt = new DecimalFormat("#0.000");
        return fmt.format(seconds);
    }

    /**
     * Returns {@code true} if no null arguments are provided, {@code false}
     * otherwise.
     * 
     * @param objects
     *            Objects, may be null
     * @return boolean
     */
    public static boolean noNulls(final Object... objects) {
        if (objects == null)
            return false;
        for (final Object object : objects) {
            if (object == null)
                return false;
        }
        return true;
    }

    /**
     * Returns {@code true} if null arguments are provided, {@code false}
     * otherwise.
     * 
     * @param objects
     *            Objects, may be null
     * @return boolean
     */
    public static boolean nulls(final Object... objects) {
        if (objects == null)
            return true;
        for (final Object object : objects) {
            if (object == null)
                return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code file} is non-null and can be read,
     * {@code false} otherwise.
     * 
     * @param file
     *            File; may be null
     * @return boolean
     */
    public static boolean readable(final File file) {
        if (file != null && file.canRead())
            return true;
        return false;
    }

    /**
     * Deletes the directory {@code dir}, and all of its contents.
     * 
     * @param dir
     *            {@link File}
     * @return boolean {@true} if success, {@code false} otherwise
     */
    public static boolean deleteDirectory(final File dir) {
        if (!deleteDirectoryContents(dir)) {
            return false;
        }

        return dir.delete();
    }

    /**
     * Recursively deletes all files and folders within the directory
     * <tt>dir</tt>.
     * 
     * @param dir
     *            {@link File}, the directory to empty contents for
     * @return boolean determines whether or not the delete was successful,
     *         <tt>true</tt> if success, <tt>false</tt> otherwise
     */
    public static boolean deleteDirectoryContents(final File dir) {
        if (dir == null || !dir.isDirectory()) {
            return false;
        }

        File[] files = dir.listFiles();
        for (final File file : files) {
            if (file.isDirectory()) {
                if (!deleteDirectory(file)) {
                    return false;
                }
            } else {
                if (!file.delete()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns {@code true} if {@code path} ends with
     * {@value PathConstants#BEL_SCRIPT_EXTENSION} or
     * {@value PathConstants#XBEL_EXTENSION}, {@code false} otherwise.
     * 
     * @param path
     *            {@link String} path
     * @return boolean
     */
    public static boolean isBELDocument(final String path) {
        if (path == null) {
            return false;
        }
        if (path.endsWith(BEL_SCRIPT_EXTENSION)) {
            return true;
        }
        if (path.endsWith(XBEL_EXTENSION)) {
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code file} ends with
     * {@value PathConstants#BEL_SCRIPT_EXTENSION} or
     * {@value PathConstants#XBEL_EXTENSION}, {@code false} otherwise.
     * 
     * @param file
     *            {@link File}
     * @return boolean
     */
    public static boolean isBELDocument(final File file) {
        if (file == null) {
            return false;
        }
        return isBELDocument(file.getPath());
    }

    /**
     * Returns {@code true} if {@code path} ends with
     * {@value PathConstants#BEL_SCRIPT_EXTENSION}, {@code false} otherwise.
     * 
     * @param path
     *            {@link String} path
     * @return boolean
     */
    public static boolean isBELScript(final String path) {
        if (path == null) {
            return false;
        }
        if (path.endsWith(BEL_SCRIPT_EXTENSION)) {
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code file} ends with
     * {@value PathConstants#BEL_SCRIPT_EXTENSION}, {@code false} otherwise.
     * 
     * @param file
     *            {@link File}
     * @return boolean
     */
    public static boolean isBELScript(final File file) {
        if (file == null) {
            return false;
        }
        return isBELScript(file.getPath());
    }

    /**
     * Returns {@code true} if {@code path} ends with
     * {@value PathConstants#XBEL_EXTENSION}, {@code false} otherwise.
     * 
     * @param path
     *            {@link String} path
     * @return boolean
     */
    public static boolean isXBEL(final String path) {
        if (path == null) {
            return false;
        }
        if (path.endsWith(XBEL_EXTENSION)) {
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code file} ends with
     * {@value PathConstants#XBEL_EXTENSION}, {@code false} otherwise.
     * 
     * @param file
     *            {@link File}
     * @return boolean
     */
    public static boolean isXBEL(final File file) {
        if (file == null) {
            return false;
        }
        return isXBEL(file.getPath());
    }

    /**
     * Joins the elements of the provided {@link Collection collection} into a
     * single {@link String string}, using the provided {@code separator} and
     * string elements.
     * <p>
     * For example: <br>
     * <blockquote> {@code join(asList("foo", "bar"), "*")}<br>
     * </blockquote> returns<br>
     * <blockquote> {@code "foo*bar"} </blockquote>
     * </p>
     * 
     * @param strings
     *            {@link String Strings} to join together
     * @param separator
     *            Separator {@link String string}
     * @return String
     */
    public static String join(Collection<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        if (strings != null) {
            Iterator<String> i = strings.iterator();
            while (i.hasNext()) {
                sb.append(i.next());
                if (i.hasNext()) {
                    sb.append(separator);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns true if a string contains one or more alphanumeric (i.e., the
     * {@code Alnum} character class) characters and nothing else.
     * 
     * @param s
     *            {@link String}
     * @return boolean
     */
    public static boolean isAlphanumeric(String s) {
        if (!hasLength(s)) {
            return false;
        }
        Pattern p = Pattern.compile("\\p{Alnum}+");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * Returns the first cause (<em>primium movens</em>) for a {@link Throwable
     * throwable}.
     * 
     * @param t
     *            the {@link Throwable throwable}
     * @return {@link Throwable} the first cause for the throwable, the original
     *         throwable if this is the first cause, or <tt>null</tt> if the
     *         <tt>t</tt> throwable was null
     */
    public static Throwable getFirstCause(Throwable t) {
        if (t == null) {
            return null;
        }

        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }

    /**
     * Returns the first message available in a stack trace.
     * <p>
     * This method can be used to obtain the first occurrence of
     * {@link Exception#getMessage() the exception message} through a series of
     * {@link Exception#getCause() causes}.
     * </p>
     * 
     * @param t
     *            the {@link Throwable throwable}
     * @return {@link String}; may be null
     */
    public static String getFirstMessage(Throwable t) {
        if (t == null) {
            return null;
        }

        String ret = t.getMessage();
        Throwable cause = t;
        while (cause.getCause() != null) {
            if (cause.getMessage() != null) {
                ret = cause.getMessage();
            }
            cause = cause.getCause();
        }
        return ret;
    }

    /**
     * Returns the virtual machine's process identifier.
     * 
     * @return {@code int}
     */
    public static int getPID() {
        if (pid == -1) {
            RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();
            String name = mx.getName();
            String token = name.split("@")[0];
            pid = parseInt(token);
        }
        return pid;
    }

    /**
     * Returns the {@link Thread#currentThread() current thread's} identifier.
     * 
     * @return {@code long}
     */
    public static long getThreadID() {
        return currentThread().getId();
    }

    /**
     * Concatenates the two arrays {@code a} and {@code b} and returns the
     * result.
     * 
     * @param a
     *            {@code int[]} a
     * @param b
     *            {@code int[]} b
     * @return {@code int[]} concatenation
     */
    public static int[] concatArrays(int[] a, int[] b) {
        final int alen = a.length;
        final int blen = b.length;
        if (alen == 0) {
            return b;
        }
        if (blen == 0) {
            return a;
        }
        final int[] result = new int[alen + blen];
        arraycopy(a, 0, result, 0, alen);
        arraycopy(b, 0, result, alen, blen);
        return result;
    }

    /**
     * Computes a 64bit hash code of a {@link CharSequence character sequence}.
     *
     * <p>
     * Using a 64bit hash code minimizes collisions at the cost of size.
     * </p>
     *
     * @param cs the {@link CharSequence character sequence} to hash
     * @return the 64bit hash
     */
    public static long computeHash64(CharSequence cs) {
        long h = HSTART;
        final long hmult = HMULT;
        final long[] ht = byteTable;
        final int len = cs.length();
        for (int i = 0; i < len; i++) {
            char ch = cs.charAt(i);
            h = (h * hmult) ^ ht[ch & 0xff];
            h = (h * hmult) ^ ht[(ch >>> 8) & 0xff];
        }
        return h;
    }

    private static final long[] createLookupTable() {
        long[] byteTable = new long[256];
        long h = 0x544B2FBACAAF1684L;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 31; j++) {
                h = (h >>> 7) ^ h;
                h = (h << 11) ^ h;
                h = (h >>> 10) ^ h;
            }
            byteTable[i] = h;
        }
        return byteTable;
    }
    
    /**
     * Captures all objects of type {@code <T>} contained in the provided list
     * as a new checked list.
     * 
     * @param <T>
     *            Captured type for new checked list
     * @param objects
     *            List of objects
     * @param t
     *            Class type to capture
     * @return Checked list of type {@code T}; may be empty
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> typedList(List<?> objects, Class<T> t) {
        if (objects == null || objects.isEmpty()) {
            return emptyList();
        }
        List<T> ret = new ArrayList<T>();
        for (final Object o : objects) {
            Class<?> oc = o.getClass();
            if (oc == t || t.isAssignableFrom(oc)) {
                ret.add((T) o);
            }
        }
        return ret;
    }

    /**
     * Closes the {@link Closeable resource} without throwing an exception.
     * 
     * @param resource
     *            the {@link Closeable resource} to close
     */
    public static void closeSilently(final Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // silently
            }
        }
    }

    /**
     * Default private constructor.
     */
    private BELUtilities() {
    }
}
