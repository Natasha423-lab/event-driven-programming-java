# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance

Observable is a class rather than an interface. Java does not support multiple class inheritance, so a class that extends Observable cannot extend another class. This limits flexibility when designing applications.

## Limitation 2: Cannot Change the Notification Order

The Observable class does not allow the programmer to control the order in which observers are notified. They are notified in an unspecified order, which can make it difficult when certain observers need to be updated first.

## Limitation 3: setChanged() is Confusing

The setChanged() method must be called before notifyObservers(). It marks the Observable object as having changed. If it is forgotten, the observers will not receive notifications even when notifyObservers() is called.

## Limitation 4: Pull Model by Default

The legacy Observer pattern uses the pull model. The observer receives a notification and then calls methods on the Observable object to retrieve the updated data. This requires extra method calls and makes the process less convenient.

## Limitation 5: Deprecated Since Java 9

Java deprecated Observer and Observable because of their design limitations. They have limited flexibility, do not provide control over notification order, and have thread-safety and extensibility problems. Modern alternatives such as custom Observer interfaces and PropertyChangeListener provide better ways to manage notifications.