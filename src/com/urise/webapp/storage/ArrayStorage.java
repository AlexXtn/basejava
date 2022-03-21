package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("ERROR: Resume " + r.getUuid() + " already exists. Impossible to save.");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Resumes for storage is overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (getIndex(r.getUuid()) == -1) {
            System.out.println("ERROR: Resume " + r.getUuid() + " is not exists. Impossible to update.");
        }
        storage[index] = r;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Resume " + uuid + " is not exists.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Resume " + uuid + " is not exists. Impossible to delete.");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме удалено из массива для хранения. Индекс удаленного резюме " + index);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
