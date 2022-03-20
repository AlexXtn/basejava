package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        System.out.println("Массив для хранения резюме очищен. Количество очищенных элементов " + size);
        size = 0;
    }

    public void save(Resume r) {
        if (resumeIsPresent(r.getUuid())) {
            System.out.println("ERROR: Resume already exists. Impossible to save.");
            return;
        }
        if (size == 10000) {
            System.out.println("ERROR: Resumes for storage is overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if (!resumeIsPresent(r.getUuid())) {
            System.out.println("ERROR: Resume is not exists. Impossible to update.");
            return;
        }
        Resume resume = get(r.getUuid());
        resume.setUuid(r.getUuid());
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (!resumeIsPresent(uuid)) {
            System.out.println("ERROR: Resume is not exists. Impossible to delete.");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                System.out.println("Резюме удалено из массива для хранения. Индекс удаленного резюме " + i);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] newStorage = new Resume[size];
        for (int i = 0; i < size; i++) {
            newStorage[i] = storage[i];
        }
        return newStorage;
    }

    public int size() {
        return size;
    }

    /**
     * @param uuid check the resume by uuid in storage;
     * @return boolean, if resume will be find by uuid then return true, else false
     */
    private boolean resumeIsPresent(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

}
