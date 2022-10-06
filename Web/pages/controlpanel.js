import {Nav} from "../components/Nav";
import {NewNotification, NewIncident} from "../components/ControlPanel";

export default function ControlPanel() {

    return(
        <section>
            <Nav/>
            <h1 className="font-bold text-2xl lg:text-4xl pt-4 pl-6 lg:pl-8 lg:pt-4 ">Bienvenido al Panel de Control</h1>
            <section className="">
                <div className="inline-block">
                    <NewNotification />
                </div>
                <div className="inline-block">
                    <NewIncident />     
                </div>
            </section>
        </section>
    );
}