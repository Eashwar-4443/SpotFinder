import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ParkingList from "../components/ParkingList";
import { getParkingArea, getParkingAreas } from "../services/ParkingService";

function ParkingLots() {
  const { id } = useParams();
  const areaId = id ? Number(id) : undefined;
  const [areaName, setAreaName] = useState("");

  useEffect(() => {
    if (!areaId) return;

    let active = true;

    const loadAreaName = async () => {
      try {
        const area = await getParkingArea(areaId);
        if (active && area?.name) {
          setAreaName(area.name);
          return;
        }
      } catch {
        // fallback below
      }

      try {
        const areas = await getParkingAreas();
        if (!active) return;
        const match = areas.find((a) => a.id === areaId || a.areaId === areaId);
        setAreaName(match?.name || "");
      } catch {
        if (active) setAreaName("");
      }
    };

    loadAreaName();

    return () => {
      active = false;
    };
  }, [areaId]);

  return (
    <div>
      <h2 className="mb-3">
        {areaName ? `${areaName} Parking Lots` : "Parking Lots"}
      </h2>
      <ParkingList areaId={areaId} />
    </div>
  );
}

export default ParkingLots;
