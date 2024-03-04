package com.dev7ex.common.map;

import java.util.*;

/**
 * @author Dev7ex
 * @since 02.11.2020
 */
@Deprecated
public class ParsedMap<K, V> extends HashMap<K, V> {

    public ParsedMap() {

    }

    public ParsedMap(final int initialCapacity) {
        super(initialCapacity);
    }

    public <V> V getValue(final K key) {
        return (V) this.get(key);
    }

    public <V> V getValue(final K key, final V defaultValue) {
        return (this.get(key) == null ? defaultValue : (V) this.get(key));
    }

    public String getString(final K key) {
        return super.get(key).toString();
    }

    public String getString(final K key, final String defaultValue) {
        return ((super.get(key).toString() == null) ? (defaultValue) : (super.get(key).toString()));
    }

    public byte getByte(final K key) {
        return Byte.parseByte(this.getString(key));
    }

    public byte getByte(final K key, final byte defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Byte.parseByte(this.getString(key))));
    }

    public short getShort(final K key) {
        return Short.parseShort(this.getString(key));
    }

    public short getShort(final K key, final short defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Short.parseShort(this.getString(key))));
    }

    public int getInteger(final K key) {
        return Integer.parseInt(this.getString(key));
    }

    public int getInteger(final K key, final int defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Integer.parseInt(this.getString(key))));
    }

    public long getLong(final K key) {
        return Long.parseLong(this.getString(key));
    }

    public long getLong(final K key, final long defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Long.parseLong(this.getString(key))));
    }

    public float getFloat(final K key) {
        return Float.parseFloat(this.getString(key));
    }

    public float getFloat(final K key, final float defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Float.parseFloat(this.getString(key))));
    }

    public double getDouble(final K key) {
        return Double.parseDouble(this.getString(key));
    }

    public double getDouble(final K key, final double defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Double.parseDouble(this.getString(key))));
    }

    public boolean getBoolean(final K key) {
        return Boolean.parseBoolean(this.getString(key));
    }

    public boolean getBoolean(final K key, final boolean defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : (Boolean.parseBoolean(this.getString(key))));
    }

    public char getCharacter(final K key) {
        return this.getString(key).charAt(0);
    }

    public char getCharacter(final K key, final char defaultValue) {
        return ((this.getString(key) == null) ? (defaultValue) : this.getString(key).charAt(0));
    }

    public UUID getUUID(final K key) {
        return UUID.fromString(this.getString(key));
    }

    public List<?> getList(final K key) {
        final Object searchedObject = this.get(key);
        return (searchedObject instanceof List ? (List<?>) searchedObject : null);
    }

    public List<String> getStringList(final K key) {
        final List<?> list = this.getList(key);
        final List<String> result = new ArrayList();
        final Iterator listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof String)) {
                continue;
            }
            result.add((String) object);
        }
        return result;
    }

    public List<Long> getLongList(final K key) {
        final List<?> list = this.getList(key);
        final List<Long> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
                continue;
            }
            result.add(((Number) object).longValue());
        }
        return result;
    }

    public List<Integer> getIntList(final K key) {
        final List<?> list = this.getList(key);
        final List<Integer> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
                continue;
            }
            result.add(((Number) object).intValue());
        }
        return result;
    }

    public List<Short> getShortList(final K key) {
        final List<?> list = this.getList(key);
        final List<Short> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
                continue;
            }
            result.add(((Number) object).shortValue());
        }
        return result;
    }

    public List<Byte> getByteList(final K key) {
        final List<?> list = this.getList(key);
        final List<Byte> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
                continue;
            }
            result.add(((Number) object).byteValue());
        }
        return result;
    }

    public List<Double> getDoubleList(final K key) {
        final List<?> list = this.getList(key);
        final List<Double> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
               continue;
            }
            result.add(((Number) object).doubleValue());
        }
        return result;
    }

    public List<Float> getFloatList(final K key) {
        final List<?> list = this.getList(key);
        final List<Float> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Number)) {
                continue;
            }
            result.add(((Number) object).floatValue());
        }
        return result;
    }

    public List<Boolean> getBooleanList(final K key) {
        final List<?> list = this.getList(key);
        final List<Boolean> result = new ArrayList();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Boolean)) {
                continue;
            }
            result.add((Boolean) object);
        }
        return result;
    }

    public List<Character> getCharList(final K key) {
        final List<?> list = this.getList(key);
        final List<Character> result = new ArrayList<>();
        final Iterator<?> listIterator = list.iterator();

        while (listIterator.hasNext()) {
            final Object object = listIterator.next();
            if (!(object instanceof Character)) {
               continue;
            }
            result.add((Character) object);
        }
        return result;
    }

    public Map<?, ?> getMap(final K key) {
        final Object searchedObject = this.get(key);
        return (searchedObject instanceof Map ? (Map<?, ?>) searchedObject : null);
    }

    public Map<?, String> getStringMap(final K key) {
        final Map<?, ?> map = this.getMap(key);
        final Map<K, String> result = new HashMap<>();
        final Iterator<?> keyIterator = map.keySet().iterator();

        while (keyIterator.hasNext()) {
            final Object keyObject = keyIterator.next();

            if (!(map.get(keyObject) instanceof String)) {
                continue;
            }
            result.put((K) keyObject, (String) map.get(keyObject));
        }
        return result;
    }

}
