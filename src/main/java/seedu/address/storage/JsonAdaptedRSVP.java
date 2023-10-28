package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.rsvp.RSVP;
import seedu.address.model.person.Person;
import seedu.address.model.rsvp.RSVPStatus;

/**
 * Jackson-friendly version of {@link RSVP}.
 */
class JsonAdaptedRSVP {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "RSVP's %s field is missing!";

    private final JsonAdaptedEvent event;
    private final JsonAdaptedPerson person;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedRSVP} with the given rsvp details.
     */
    @JsonCreator
    public JsonAdaptedRSVP(@JsonProperty("event") JsonAdaptedEvent event,
                             @JsonProperty("person") JsonAdaptedPerson person,
                             @JsonProperty("status") String status) {
        this.event = event;
        this.person = person;
        this.status = status;
    }

    /**
     * Converts a given {@code RSVP} into this class for Jackson use.
     */
    public JsonAdaptedRSVP(RSVP source) {
        event = new JsonAdaptedEvent(source.getEvent());
        person = new JsonAdaptedPerson(source.getPerson());
        status = source.getRsvpStatus().getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public RSVP toModelType() throws IllegalValueException, ParseException {
        if (event == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Event.class.getSimpleName()));
        }
        if (person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, RSVPStatus.class.getSimpleName()));
        }
        RSVPStatus rsvpStatus = RSVPStatus.getRSVPStatus(status);
        return new RSVP(event.toModelType(), person.toModelType(), rsvpStatus);
    }
}
