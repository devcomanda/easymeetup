export function eventsMiddleware({ dispatch }) {
  return function(next) {
    return function(action) {
      if (action.type === 'GET_EVENT') {
        console.log('message from eventmiddlewire');
      }
      return next(action);
    };
  };
}