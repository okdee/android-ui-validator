/**
 * example package name:
 * package com.example.packagename;
 */
package YOUR_PACKAGE_NAME_HERE;

import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

final public class UIValidator {
    static final public String requiredFieldString = "Required Field!";
    static final public String spinnerErrorValue1 = "Select...";
    static final public String spinnerErrorValue2 = "";


    private UIValidator() {
    }


    final public static boolean validateViewFields(List<View> views) {
        return validateViewFields(views, false);
    }


    final public static boolean validateViewFields(List<View> views, boolean applyVisuals) {
        if (views == null)
            return false;

        List<Boolean> validationsResults = new ArrayList<>();

        validationsResults.add(validateTextViewFields(views, applyVisuals));
        validationsResults.add(validateSpinnerViewFields(views, applyVisuals));

        for (boolean result : validationsResults) {
            if (result == false) {
                return false;
            }
        }

        return true;
    }


    final public static boolean validateSpinnerViewFields(List<View> views, boolean applyVisuals) {
        if (views == null)
            return false;

        boolean validationResult = true;
        for (View spinner : views) {
            if (spinner != null) {
                if (spinner instanceof Spinner) {
                    Object spinnerSelectedItem = ((Spinner) spinner).getSelectedItem();

                    if (spinnerSelectedItem == null) {
                        validationResult = false;
                    } else {
                        if (spinnerSelectedItem.toString().equals(spinnerErrorValue1) ||
                                spinnerSelectedItem.toString().equals(spinnerErrorValue2)) {
                            validationResult = false;
                            if (applyVisuals)
                                setError(spinner);
                        } else {
                            if (applyVisuals)
                                removeError(spinner);
                        }
                    }
                }
            }
        }

        return validationResult;
    }


    final public static boolean validateSpinnerViewFields(List<View> views) {
        return validateSpinnerViewFields(views, false);
    }


    final public static boolean validateTextViewFields(List<View> views, boolean applyVisuals) {
        if (views == null)
            return false;

        boolean validationResult = true;
        for (View field : views) {
            if (field instanceof TextView) {
                if (TextUtils.isEmpty(((TextView) field).getText().toString().trim())) {
                    validationResult = false;
                    if (applyVisuals)
                        setError(field);
                } else {
                    if (applyVisuals)
                        removeError(field);
                }
            }
        }

        return validationResult;
    }


    final public static boolean validateTextViewFields(List<View> views) {
        return validateTextViewFields(views, false);
    }


    final public static void setError(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setError(requiredFieldString);
            return;
        }
        if (view instanceof Spinner) {
            ((TextView) ((Spinner) view).getSelectedView()).setError(requiredFieldString);
        }
    }


    final public static void setErrors(List<View> views) {
        for (View view : views) {
            setError(view);
        }
    }


    final public static void removeError(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setError(null);
            return;
        }
        if (view instanceof Spinner) {
            ((TextView) ((Spinner) view).getSelectedView()).setError(null);
        }
    }


    final public static void removeErrors(List<View> views) {
        for (View view : views) {
            removeError(view);
        }
    }
}
