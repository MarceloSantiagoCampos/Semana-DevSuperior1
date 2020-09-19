export type GamePlatform = 'XBOX' | 'PC' | 'PLAYSTATION';

export type Game = {
    //Dados do banco de dados respondidos pela api
    id: number;
    title: string;
    platform: GamePlatform;

    //necessário para integrar com o RNPickerSelect 
    label: string;
    value: number;
}