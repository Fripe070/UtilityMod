package io.github.fripe070.utilitymod.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import static io.github.fripe070.utilitymod.client.UtilitymodClient.FLIGHT_KEY;
import static net.minecraft.entity.LivingEntity.GRAVITY;

public class Flying {
    private static final double SPRINT_MULTIPLIER = 2;
    private static final int ANTI_KICK_DELAY = 60;
    private static final Vec3d verticalSpeed = new Vec3d(0, GRAVITY * 5, 0);
    static int flightTicks = 0;
    static boolean upBoostToggle = false;
    
    public static void tick(MinecraftClient client) {
        if (
                client.player == null ||
                client.player.isCreative() ||
                client.player.isSpectator() ||
                !FLIGHT_KEY.isPressed()
        ) {
            flightTicks = 0;
            return;
        }

        // Variables that only get used once 😎
        boolean forward = client.options.forwardKey.isPressed();
        boolean back = client.options.backKey.isPressed();
        boolean left = client.options.leftKey.isPressed();
        boolean right = client.options.rightKey.isPressed();
        boolean jump = client.options.jumpKey.isPressed();
        boolean sneak = client.options.sneakKey.isPressed();
        boolean sprint = client.options.sprintKey.isPressed();

        Entity flightEntity = client.player;
        if (client.player.hasVehicle() && client.player.getVehicle() != null) {
            flightEntity = client.player.getVehicle();
            // This makes flying boats a nicer experience
            flightEntity.setYaw(client.player.getYaw());
        }

        double sprintMultiplier = sprint ? SPRINT_MULTIPLIER : 1;
        Vec3d velocity = flightEntity.getVelocity();
        Vec3d forwardsDirection = client.player.getRotationVec(client.getTickDelta())
                .multiply(sprintMultiplier) // Make WASD movements more extreme when sprinting
                .multiply(0.2); // Not THAT fast
        Vec3d newVelocity = new Vec3d(velocity.x, 0, velocity.z);

        if (forward) newVelocity = newVelocity.add(forwardsDirection);
        if (back) newVelocity = newVelocity.add(forwardsDirection.negate());
        if (left) newVelocity = newVelocity.add(forwardsDirection.rotateY((float) (Math.PI/2)));
        if (right) newVelocity = newVelocity.add(forwardsDirection.rotateY((float) (-Math.PI/2)));

        if (jump) newVelocity = newVelocity.add(verticalSpeed.multiply(sprintMultiplier));
        if (sneak) newVelocity = newVelocity.add(verticalSpeed.negate().multiply(sprintMultiplier));

        // Boost up again to undo the drop made by the kick prevention code
        if (upBoostToggle) {
            newVelocity = new Vec3d(newVelocity.x, newVelocity.y + GRAVITY, newVelocity.z);
            upBoostToggle = false;
        }

        // Game doesn't care when using an elytra
        if (!client.player.isFallFlying()) {
            // Prevent the player from getting kicked for flying
            // The gravity is... close enough to the value that the server starts complaining at, so I'm using that
            if (flightTicks >= ANTI_KICK_DELAY && newVelocity.y > -GRAVITY) {
                newVelocity = new Vec3d(newVelocity.x, -GRAVITY, newVelocity.z);
                upBoostToggle = true;
            }

            // Reset flight tick counter when anti-kick measures have been taken (or you stopped flying)
            if (flightTicks >= ANTI_KICK_DELAY || newVelocity.y <= -GRAVITY || client.player.isOnGround()) flightTicks = 0;
        }

        flightEntity.setVelocity(newVelocity);

        // TODO: Add noFall

        flightTicks++; // Look mom, a tick!
    }
}
