# Week 1 - Activity 4: Debugging Event Handlers

## Bug 1: Blocking the Event Dispatch Thread (EDT)

**Symptom:** The application freezes for five seconds when the button is clicked.

**Cause:** `Thread.sleep(5000)` was executed on the Event Dispatch Thread, preventing the GUI from responding to other events.

**Fix:** Moved the time-consuming operation to a background thread and used `SwingUtilities.invokeLater()` to update the GUI.

## Bug 2: Missing Event Listener

**Symptom:** Clicking the button does not produce any response.

**Cause:** The button did not have an ActionListener attached to it.

**Fix:** Added an ActionListener to the button so that it responds to clicks and updates the counter.

## Bug 3: Updating GUI from the Wrong Thread

**Symptom:** The background thread updates the label directly, which can cause unpredictable GUI behaviour.

**Cause:** Swing components were being updated outside the Event Dispatch Thread.

**Fix:** Used `SwingUtilities.invokeLater()` to make sure the label is updated safely on the EDT.

## Bug 4: Memory Leak

**Symptom:** Removed buttons remain referenced in the application.

**Cause:** The buttons were removed from the panel but were still stored in the `createdButtons` list.

**Fix:** Added `createdButtons.clear()` after removing the buttons to release the stored references.

## Conclusion

This activity helped me understand common problems in event-driven programming. I learned the importance of keeping the GUI responsive, attaching event listeners correctly, updating Swing components on the correct thread, and managing memory properly.