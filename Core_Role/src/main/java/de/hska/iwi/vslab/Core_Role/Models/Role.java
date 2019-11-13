package de.hska.iwi.vslab.Core_Role.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String typ;
    private int level;

    protected Role() {
    }

    public Role(String typ, int level) {
        this.typ = typ;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("Role[id=%d, typ='%s', level=%d]", id, typ, level);
    }

    public int getId() {
        return id;
    }

    public String getTyp() {
        return typ;
    }

    public int getLevel() {
        return level;
    }
}