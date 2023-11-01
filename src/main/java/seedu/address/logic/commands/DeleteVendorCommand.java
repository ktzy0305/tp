package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;

/**
 * Deletes a vendor identified using its displayed index from the address book.
 */
public class DeleteVendorCommand extends Command {

    public static final String COMMAND_WORD = "deleteVendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the vendor identified by the index number used in the displayed vendor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " vdr/1";

    public static final String MESSAGE_DELETE_VENDOR_SUCCESS = "Deleted Vendor: %1$s";

    private final Index targetIndex;

    public DeleteVendorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Vendor> lastShownList = model.getFilteredVendorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDOR_DISPLAYED_INDEX);
        }

        Vendor vendorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteVendor(vendorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_VENDOR_SUCCESS, Messages.format(vendorToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteVendorCommand)) {
            return false;
        }

        DeleteVendorCommand otherDeleteCommand = (DeleteVendorCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}