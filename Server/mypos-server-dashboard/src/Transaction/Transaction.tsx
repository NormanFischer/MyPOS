export type Transaction = {
    user: string,
    datetime: string
    total: number
}

export const TransactionConstructor = class implements Transaction {
    user: string = "";
    datetime: string = "";
    total: number = -1;
}