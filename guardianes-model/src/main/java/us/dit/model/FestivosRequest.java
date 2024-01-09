package us.dit.model;

import java.util.Arrays;

public class FestivosRequest {
    private String[] festivos;

    public String[] getFestivos() {
        return festivos;
    }

    public void setFestivos(String[] festivos) {
        this.festivos = festivos;
    }

    @Override
    public String toString() {
        return "FestivosRequest{" +
                "festivos=" + Arrays.toString(festivos) +
                '}';
    }
}
