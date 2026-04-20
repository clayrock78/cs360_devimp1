# Refactors Applied in `cs360_devimp2`

This documents the 35 refactors applied inside the `cs360_devimp2` folder.

## Rename refactors

1. Renamed `Appointment.confirmed` to `confirmed` with primitive `boolean` usage normalized around `isConfirmed()`.
2. Renamed `SchedulerService.tutorSchedule` to `reservedDatesByTutorId` to reflect its actual purpose.
3. Renamed `Tutor.avaliability` to `availability`.
4. Renamed `Tutor.randomizeAvailabity()` to `randomizeAvailability()`.
5. Renamed `Tutor.isAvaliable()` to `isAvailable()`.
6. Renamed `User.ID` to `id` and introduced `getId()` while keeping `getID()` as a compatibility wrapper.
7. Renamed `Support.ticketList` to `tickets`.

## Extract method refactors

8. Extracted `Appointment.validateDate(int)` from `setDate(int)`.
9. Extracted `AppointmentService.ensureTutorAvailability(Tutor, int)` from booking flow.
10. Extracted `AppointmentService.createAppointment(Student, Tutor, int)` from booking flow.
11. Extracted `Calendar.validateCalendar(BitSet)` from `printCalendar(BitSet, int)`.
12. Extracted `Calendar.printHeader()` from `printCalendar(BitSet, int)`.
13. Extracted `Calendar.printSlot(BitSet, int, int, int)` from `printCalendar(BitSet, int)`.
14. Extracted `Calendar.shouldStartNewLine(int)` from `printCalendar(BitSet, int)`.

## Extract interface/class refactors

15. Extracted `AppointmentNotifier` interface from notification behavior used by `AppointmentService`.
16. Updated `NotificationService` to implement `AppointmentNotifier`.
17. Extracted `TutorScheduler` interface from scheduling behavior used by `AppointmentService`.
18. Updated `SchedulerService` to implement `TutorScheduler`.
19. Extracted `NotificationService.buildTutorConfirmationMessage(Appointment)` to isolate message creation.
20. Extracted `NotificationService.buildStudentConfirmationMessage(Appointment)` to isolate message creation.
21. Extracted `TutorCatalog.addSampleTutor(String, String, List<String>)` from sample tutor setup.

## Extract constant / magic number cleanup refactors

22. Extracted `Appointment.MIN_DATE`.
23. Extracted `Appointment.MAX_DATE`.
24. Extracted `Calendar.DAYS_IN_MONTH`.
25. Extracted `Calendar.DAYS_IN_WEEK`.
26. Extracted `Calendar.TOTAL_SLOTS`.
27. Extracted `Tutor.DAYS_IN_MONTH`.
28. Replaced repeated menu prompt strings in `Main` with shared prompt helper usage rather than inline scanner interaction.

## Encapsulation and field cleanup refactors

29. Made `Appointment.student` and `Appointment.tutor` immutable with `final` fields.
30. Made `AppointmentService` dependencies and appointment list `final`.
31. Made `SchedulerService` schedule storage `final` and routed access through `getReservedDates(Tutor)`.
32. Made `SessionNote.notes` private and final instead of package-visible mutable state.
33. Removed the redundant standalone `SessionNote.note` field and unified note retrieval through the backing list.
34. Initialized `Tutor.tutoringSubjects` eagerly and copied incoming subject lists in `setTutoringSubjects(List<String>)`.
35. Made `Support.tickets` private and final.

## Additional maintenance-oriented cleanup completed alongside the refactors

- Simplified `Main` menu code with `prompt`, `readMenuChoice`, `printStudentMenu`, `printTutorMenu`, and `printTutorNames` helpers.
- Fixed `Tutor.setName(String)` so it delegates to `super.setName(name)` instead of recursing.
- Fixed `TutorCatalog.findTutorsByName(String)` to throw `TutorNotFoundException` when no matches are found, aligning it with existing calling code.
- Fixed `TutorNotFoundException` constructors so they construct the exception correctly.
- Simplified `User` ID generation so automatically assigned IDs advance correctly.
