export type Item = {
    sku: string,
    itemName: string,
    cost: string,
    quantity: number
}

export const ItemConstructor = class implements Item {
    sku: string = "";
    itemName: string = "";
    cost: string = "";
    quantity: number = -1;
}