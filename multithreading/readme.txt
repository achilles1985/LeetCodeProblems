shotdown() vs shotdownNow()
shotdown() - stops accepting new tasks, finish all tasks that are already executing on the thread pool and those that are queued in tasksQueue.
shotdownNow() - stops accepting new tasks, finish all tasks that are already executing on the thread pool, and return tasks that are queued not executing them.

