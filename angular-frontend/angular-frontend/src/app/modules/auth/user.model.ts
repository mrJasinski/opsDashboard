export class User
{
    constructor(
        public email: string
        , public token: string
        , public minutesLeft: number)
    {
    }
}