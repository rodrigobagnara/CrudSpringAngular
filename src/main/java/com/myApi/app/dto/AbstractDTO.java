package com.myApi.app.dto;

import java.util.Objects;

/**
 * @author Rodrigo Bagnara
 * @date 22/10/2024
 */

public abstract class AbstractDTO {

    @Override
    public boolean equals(Object other) {

        if (other instanceof AbstractDTO) {
            AbstractDTO otherModel = (AbstractDTO) other; // Casting para objeto AbstractDTO
            return Objects.equals(getId(), otherModel.getId());
        }
        return false;
    }

    public abstract Long getId();

    @Override
    public int hashCode() {
        return Objects.nonNull(getId()) ? getId().hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getClass().getName()).append("[").append(getId()).append("]").toString();
    }

}