package ual.hmis.sesion06;

import java.util.Locale;

public final class ConversionTemperatura {

    private static final String CELSIUS = "celsius";
    private static final String FAHRENHEIT = "fahrenheit";
    private static final String KELVIN = "kelvin";

    public double convertTemperature(double temperature, String fromUnit, String toUnit) {
        if (fromUnit == null || toUnit == null) return Double.NaN;

        String from = fromUnit.toLowerCase(Locale.ROOT);
        String to   = toUnit.toLowerCase(Locale.ROOT);

        return switch (from) {
            case CELSIUS -> switch (to) {
                case FAHRENHEIT -> temperature * 9 / 5 + 32;
                case KELVIN     -> temperature + 273.15;
                case CELSIUS    -> temperature;
                default         -> Double.NaN;
            };
            case FAHRENHEIT -> switch (to) {
                case CELSIUS    -> (temperature - 32) * 5 / 9;
                case KELVIN     -> (temperature - 32) * 5 / 9 + 273.15;
                case FAHRENHEIT -> temperature;
                default         -> Double.NaN;
            };
            case KELVIN -> switch (to) {
                case CELSIUS    -> temperature - 273.15;
                case FAHRENHEIT -> (temperature - 273.15) * 9 / 5 + 32;
                case KELVIN     -> temperature;
                default         -> Double.NaN;
            };
            default -> Double.NaN;
        };
    }
}

