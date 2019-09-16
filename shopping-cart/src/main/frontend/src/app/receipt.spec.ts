import { Receipt } from './receipt';

describe('Receipt', () => {
  it('should create an instance', () => {
    expect(new Receipt(0,0,0)).toBeTruthy();
  });
});
