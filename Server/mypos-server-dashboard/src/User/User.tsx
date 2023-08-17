export type User = {
    name: string
}

export const UserConstructor = class implements User {
    name: string = "";
}