package com.yugahashimoto.andcode.runtime

import com.yugahashimoto.andcode.R

/**
 * A coding agent that can be installed into the shared Android-local Linux sandbox.
 *
 * Agents share the same Android-local Linux sandbox, but each is provisioned by its own official
 * distribution channel: OpenCode and Pi as verified release archives, Claude Code through apk, and
 * Antigravity in its glibc Debian guest rootfs.
 */
enum class LocalAgent(
    val id: String,
    val displayNameRes: Int,
    val targetId: String,
    /** Marks which agent a row belongs to where the name does not fit - drawer chats, for one. */
    val iconRes: Int,
) {
    OPEN_CODE("opencode", R.string.agent_opencode_name, "local-android", R.drawable.ic_agent_opencode),
    CLAUDE_CODE("claude-code", R.string.agent_claude_code_name, "claude-code-local", R.drawable.ic_agent_claude),
    ANTIGRAVITY("antigravity", R.string.agent_antigravity_name, "antigravity-local", R.drawable.ic_agent_antigravity),
    PI("pi", R.string.agent_pi_name, "pi-local", R.drawable.ic_agent_pi),
    ;

    companion object {
        fun fromId(id: String): LocalAgent? = entries.firstOrNull { it.id == id }
    }
}
