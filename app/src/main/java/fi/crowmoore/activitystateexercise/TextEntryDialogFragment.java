package fi.crowmoore.activitystateexercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Crowmoore on 09/10/2016.
 */

public class TextEntryDialogFragment extends DialogFragment {

    public interface TextEntryDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String text);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    TextEntryDialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (TextEntryDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement TextEntryDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.text_entry_dialog, null);
        builder.setView(dialogView)
                .setTitle("Give me a new text")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText edit = (EditText) dialogView.findViewById(R.id.edit);
                        String text = edit.getText().toString();
                        listener.onDialogPositiveClick(TextEntryDialogFragment.this, text);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(TextEntryDialogFragment.this);
            }
        });
        return builder.create();
    }
}
