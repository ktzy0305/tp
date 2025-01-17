package seedu.address.model.rsvp;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.event.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a RSVP in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Rsvp {
    private Event event;
    private Person person;
    private RsvpStatus rsvpStatus;

    /**
     * Every field must be present and not null.
     */
    public Rsvp(Event event, Person person, RsvpStatus rsvpStatus) {
        requireAllNonNull(event, person, rsvpStatus);
        this.event = event;
        this.person = person;
        this.rsvpStatus = rsvpStatus;
    }

    public Event getEvent() {
        return this.event;
    }

    public Person getPerson() {
        return this.person;
    }

    public RsvpStatus getRsvpStatus() {
        return rsvpStatus;
    }

    /**
     * Returns true if both rsvp have the same event and person.
     */
    public boolean isSameRsvp(Rsvp otherRsvp) {
        if (otherRsvp == this) {
            return true;
        }

        return otherRsvp != null
                && otherRsvp.getEvent().equals(getEvent())
                && otherRsvp.getPerson().equals(getPerson());
    }

    // To Resolve Law of Demeter.
    public Name getPersonName() {
        return person.getName();
    }

    public seedu.address.model.event.Name getEventName() {
        return event.getName();
    }

    public List<Person> getEventGuests() {
        return event.getPersons();
    }

    //For testing
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Rsvp)) {
            return false;
        }

        Rsvp otherRsvp = (Rsvp) other;
        return event.equals(otherRsvp.event)
                && person.equals(otherRsvp.person)
                && rsvpStatus.equals(otherRsvp.rsvpStatus);
    }
}
